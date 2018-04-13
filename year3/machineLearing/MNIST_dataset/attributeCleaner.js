/**
 * Attribute Cleaner script for MNIST data set.
 *
 * This script removes feature with predetermined values
 *
 * This script requires the .arffParser.js file, available from 
 * https://gist.github.com/njdart/e9499fcf9448ebc539f0ebe1fd8ba969
 * 
 * Built using node v6.9.0
 *
 *
 * USAGE:
 *
 * node attributeCleaner.js dataSet [testSet]
 *
 */

var parser = require('./arffParser.js');

console.log(`Reading file ${process.argv[2]}`);

parser.readData(process.argv[2], (err, data, attributes) => {

  // Remove the class attribute as we dont care about it
  var classAttribute = attributes.pop();

  var removeAttributesIdxs = [];

  attributes.forEach( (attribute, idx) => {

    var attributeSum = 0;

    for (var i = 0; i < data.length; i++) {
      attributeSum += data[i][idx];
    }

    var mean = attributeSum / data.length;
    var deviations = [];

    for (var i = 0; i < data.length; i++) {
      deviations[i] = Math.pow(data[i][idx] - mean, 2);
    }

    var variance = deviations.reduce((total, current) => {
      return total + current;
    }, 0) / data.length;

    var stdDev = Math.sqrt(variance);

    if (stdDev < 2) {
      removeAttributesIdxs.push(idx);
      console.log(`Marking attribute ${attribute.name} for removal (mean: ${mean}, Variance: ${variance}, stdDev: ${stdDev})`);
    }

  });

  console.log(`Removing ${removeAttributesIdxs.length} attributes`);

  // Remove from the end to keep index positions valid as we go through
  removeAttributesIdxs.reverse()

  removeAttributesIdxs.forEach( (removeIdx) => {
    attributes.splice(removeIdx, 1);

    for (var i = 0; i < data.length; i++) {
      data[i].splice(removeIdx, 1);
    }
  });

  var outputName = process.argv[2] + '_cleaned_2.arff';

  console.log(`writing ${process.argv[2]} to ${outputName}...`);

  // Put the class attribute back for writing
  attributes.push(classAttribute);

  parser.writeData(outputName, 'MNIST-576', attributes, data, () => {
    console.log('Written');

    if (process.argv[3]) {

      console.log(`Removing attributes from test set ${process.argv[3]}`);

      parser.readData(process.argv[2], (err, data, attributes) => {

        removeAttributesIdxs.forEach( (removeIdx) => {
          attributes.splice(removeIdx, 1);

          for (var i = 0; i < data.length; i++) {
            data[i].splice(removeIdx, 1);
          }
        });

        var outputName = process.argv[3] + '_cleaned_2.arff';

        console.log(`writing ${process.argv[3]} to ${outputName}...`);

        parser.writeData(outputName, 'MNIST-576', attributes, data, () => { console.log('Written. Done.') });

      });
    }
  });
});