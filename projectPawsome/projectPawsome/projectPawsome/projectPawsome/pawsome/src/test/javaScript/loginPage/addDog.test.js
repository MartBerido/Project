const { validateForm } = require('./addDog.js');
const { JSDOM } = require('jsdom');
const { document } = new JSDOM('').window;

global.document = document;
global.alert = jest.fn();

test('Return true if the form is valid', () => {

    document.body.innerHTML = `
    <form onsubmit="return validateForm()">
      <input type="text" id="dogName" value="Fido">
      <input type="text" id="dogBreed" value="Labrador">
      <input type="text" id="dogAge" value="3">
      <input type="submit" value="Submit">
    </form>
  `;

    expect(validateForm()).toBe(true);
});

test('Return false if the form is invalid', () => {

    document.body.innerHTML = `
    <form onsubmit="return validateForm()">
      <input type="text" id="dogName" value="123"> 
      <input type="text" id="dogBreed" value="Golden Retriever 123"> 
      <input type="text" id="dogAge" value="20"> 
      <input type="submit" value="Submit">
    </form>
  `;

    expect(validateForm()).toBe(false);
});