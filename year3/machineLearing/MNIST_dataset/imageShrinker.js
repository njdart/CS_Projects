const ArffParser = require('./arffParser.js');
const util = require('util');

let scaleFactor = 28;

let f = new ArffParser('/home/nic/git/njdart/aber-cs/CS36110_assignment_2/mnist748-train1500.arff');

const imageClass = f.attributes.pop();

f.dropWhere( (attribute, index) => {

  const col = Math.floor((index % 28) / scaleFactor) * scaleFactor;
  const row = Math.floor(index / (28 * scaleFactor)) * scaleFactor;

  const scaledImageIndex = col + (row * 28);

  const drop = scaledImageIndex !== index;

  if (drop) {

    attribute.data.forEach( (d, i) => {
      f.attributes[scaledImageIndex].data[i] += d;
    });

  }

  return drop;

});

f.attributes.forEach( (attribute, index) => {

  if (!attribute.drop) {
    attribute.data = attribute.data.map( (d) => {
      return (Math.round(d / 4) > 128) ? 1 : 0 ; // Average the pixel values and binarize it
    });
  }

});

f.attributes.push(imageClass);

f.writeFile('/tmp/7x7.arff');

// for(let index = 0; index < 784; index++) {

//   const col = Math.floor((index % 28) / scaleFactor) * scaleFactor;
//   const row = Math.floor(index / (28 * scaleFactor)) * scaleFactor;

//   const pos = col + (row * 28);

//   // process.stdout.write(pad(col));
//   // process.stdout.write(pad(row));
//   process.stdout.write(pad(pos));

//   if ((index + 1) % 28 === 0) {
//     process.stdout.write('\n');
//   }
// }

// function pad(a) {
//   if ((a+'').length === 1) {
//     return `${a}   `;
//   } else if ((a+'').length === 2) {
//     return `${a}  `;
//   } else {
//     return `${a} `;
//   }
// }