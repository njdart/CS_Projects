const fs = require('fs');
const path = require('path');

class ArffParser {

  constructor(fileName) {
    this.fileName = fileName;
    this.attributes = [];
    this._parserErrors = [];
    this._relation = '';
    this._readData();
    this.dateTime = new Date().toISOString();
  }

  writeFile(fileName) {
    if (!fileName) {
      const p = path.parse(this.fileName);
      fileName = `${p.dir}/${p.name}-${this.dateTime}.arff`;
    }

    this._writeFile(fileName, this.attributes, this._relation)
  }

  dropWhere(test) {

    let dropCount = 0;

    this._calculateStatistics();

    this.attributes.forEach( (attribute, index) => {

      if (test(attribute, index)) {
        attribute.drop = true;
        dropCount++;
      } else {
        attribute.drop = false;
      }
    });

    return dropCount;
  }

  repeatDropOnFiles() {

    let outputFiles = [];

    for (let key in arguments) {

      const file = arguments[key];

      const p = path.parse(file);
      let f = new ArffParser(file);

      f.dropWhere( (attr, index) => {
        return this.attributes[index].drop
      });

      const newFilePath = `${p.dir}/${p.name}-${this.dateTime}.arff`;

      f.writeFile(newFilePath);
      outputFiles.push(newFilePath);
    }

    return outputFiles;
  }

  applyTransformOnFeatureInstances(transformation, where) {

    let transformed = 0;

    this.attributes.forEach( (attribute, index) => {

      if (where(attribute, index)) {
        transformed += attribute.count
        attribute.data.map(transformation)
      }
    });

    this._calculateStatistics();

    return transformed;
  }

  visualise(width) {
    if (!width) {
      width = 28;
    }

    this.attributes.forEach( (attribute, index) => {
      if (attribute.drop) {
        process.stdout.write('_');
      } else {
        process.stdout.write('X');
      }

      if ((index + 1) % width === 0) {
        process.stdout.write('\n');
      }
    });
  }

  _makeAttribute(name, type) {
    return {
        name: name,
        type: type,
        data: [],
        sum: 0,
        count: 0,
        mean: 0,
        standardDeviation: 0,
        drop: false
      }
  }

  _readData() {

    let file = fs.readFileSync(this.fileName).toString().split('\n')
    let lineNumber = 0;
    let line;

    while( (line = file.shift()) !== undefined ) {
      lineNumber++;

      if (line.length <= 0 || line.match(/^\s*$/)) {
        continue;
      }

      if (line.match(/^@relation/i)) {
        this._relation = line.split(/\s+/)[1];
        continue;
      }

      if (line.match(/^@attribute/i)) {
        let attribute = line.split(/\s+/);
        this.attributes.push(this._makeAttribute(attribute[1], attribute[2]));
        continue;
      }

      if (line.match(/^@data$/i)) {
        // Ignore it
        continue;
      }

      let instanceData = line.split(/,/).map(parseFloat);

      if (instanceData.length !== this.attributes.length) {
        this._parserErrors.push({
          line: lineNumber,
          expectedLength: this.attributes.length,
          actualLength: instanceData.length
        });
      } else {
        instanceData.forEach( (value, index) => {
          this.attributes[index].data.push(value);
          this.attributes[index].sum += value;
          this.attributes[index].count++;
        });
      }
    }
  }

  _calculateStatistics() {
    this.attributes.forEach( (attribute) => {
      attribute.mean = attribute.sum / attribute.count;

      let varianceSum = 0;

      attribute.data.forEach( (d) => {
        varianceSum += Math.pow(d - attribute.mean, 2);
      });

      attribute.standardDeviation = Math.sqrt(varianceSum);
    });
  }

  _writeFile (fileName, attributes, relation) {

    let header = `@relation ${relation}\n\n`;

    let instanceData = [];

    attributes.forEach( (attribute) => {

      if (attribute.drop) {
        return;
      }

      header += `@attribute ${attribute.name} ${attribute.type}\n`;

      attribute.data.forEach( (d, idx) => {
        if (!instanceData[idx]) {
          instanceData[idx] = [];
        }

        instanceData[idx].push(d);
      });
    });

    header += '\n\n@data\n';

    instanceData.map( (instance) => {
      return instance.join(',');
    })

    instanceData = instanceData.join('\n');

    fs.writeFileSync(fileName, header + instanceData)
  }
}

module.exports = ArffParser;

if (!module.parent) {
  const argv = require('minimist')(process.argv.slice(2));

  console.log(argv);

  const file = new ArffParser(argv['_'][0])

  let dropped = file.dropWhere( (attribute) => {
    return attribute.name !== 'class' && attribute.mean < 1;
  });

  console.log('Dropped', dropped, 'features')

  // let changed = file.applyTransformOnFeatureInstances( (d) => {
  //   return (d > 128) ? 1 : 0;
  // }, (attr, idx) =>  {
  //   return attr.name !== 'class';
  // });
  //
  // console.log('Applied transformation on', changed, 'values')

  file.visualise();

  file.writeFile(argv.o || null);

  if (argv.t) {
    console.log(file.repeatDropOnFiles.apply(file, argv.t))
  }

}
