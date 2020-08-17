/// <reference types="Cypress"/>

describe("New post on Conduit", () => {
  beforeEach(() => {
    // cy.registerUserIfNeeded();
    // cy.login();
  });
  it("Write a new post", () => {
    const email = "visit0344314@gmail.com";
    const password = "testing1234";
    cy.visit("https://demo.realworld.io/#/");
    cy.contains("a.nav-link", "Sign in").click();
    cy.get("[placeholder=Email]").type(email);
    cy.get("[placeholder=Password]").type(password);
    cy.get("form").submit();
    cy.contains("a.nav-link", "Your Feed").should("be.visible");
    cy.get('a[href*="editor"]').click();
    cy.get("input:first").type("vantest1");
    cy.get("fieldset:nth-of-type(2)").type("vantest11");
    cy.get("fieldset:nth-of-type(3)").type("vantest111");
    cy.get("fieldset:nth-of-type(4)").type("vantest1111");
    cy.get(".btn").click();
    cy.get(
      "body > div > app-header > nav > div > ul:nth-child(3) > li:nth-child(1) > a"
    ).click();
    cy.get(
      "body > div > div > div > div.container.page > div > div.col-md-9 > div > ul > li:nth-child(2) > a"
    ).click();
    cy.get("body > div > div > div > div.banner > div > h1").should(
      "be.visible"
    );
  });
});
