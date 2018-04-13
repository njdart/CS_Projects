const ArffParser = require('./arffParser.js');

const a = new ArffParser('/home/nic/git/njdart/aber-cs/CS36110_assignment_2/mnist748-train1500.arff');
const b = new ArffParser('/tmp/biggerSet.arff');

b.attributes.pop(); // Remove image class from b

let f;
let i = 0;

const imageClass = a.attributes.pop();

while ( (f = b.attributes.shift()) !== undefined) {
  f.name = `pixel-scaled17x17-${++i}`;
  a.attributes.push(f);
}

a.attributes.push(imageClass)

a.writeFile('/tmp/mnist-scaled.arff');

