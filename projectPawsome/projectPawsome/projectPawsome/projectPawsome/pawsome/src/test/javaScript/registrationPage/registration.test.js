const {JSDOM} = require('jsdom');
const {validateForm} = require('./homeRegistration.js');
const { document } = new JSDOM('').window;

global.document = document;
global.alert = jest.fn();

test('Return true if the form is valid', () => {

    document.body.innerHTML = `
    <form onsubmit="return validateForm()">
      <input type="password" id="password" value="qwerty">
      <input type="password" id="confirmPassword" value="qwerty">
      <input type="text" id="phoneNumber" value="1234567890">
      <input type="text" id="firstName" value="John">
      <input type="text" id="lastName" value="Doe">
      <input type="text" id="city" value="Douglas">
      <input type="text" id="postalCode" value="123456">
      <input type="submit" value="Submit">
    </form>
  `;

    expect(validateForm()).toBe(true);
});

test('Return false if the form is invalid', () => {

    document.body.innerHTML = `
    <form onsubmit="return validateForm()">
      <input type="password" id="password" value="password123">
      <input type="password" id="confirmPassword" value="password4">
      <input type="text" id="phoneNumber" value="1234"> 
      <input type="text" id="firstName" value="John123"> 
      <input type="text" id="lastName" value="Doe456"> 
      <input type="text" id="city" value="Douglas1">
      <input type="text" id="postalCode" value="ABCDE">
      <input type="submit" value="Submit">
    </form>
  `;

    expect(validateForm()).toBe(false);
});
