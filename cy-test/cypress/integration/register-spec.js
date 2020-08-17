/// <reference-types="Cypress" />

describe("Register", () => {
  it("Register a new user", () => {
    const username = Math.round(Math.random() * 10000) + "visit0344314";
    const email = Math.round(Math.random() * 10000) + "visit0344315@gmail.com";
    const password = "testing1234";
    cy.visit("https://demo.realworld.io/#/");
    cy.contains("a.nav-link", "Sign up").click();
    cy.get("[placeholder=Username]").type(username);
    cy.get("[placeholder=Email]").type(email);
    cy.get("[placeholder=Password]").type(password);
    cy.get("form").submit();
    cy.contains("a.nav-link", "Your Feed");
  });
});