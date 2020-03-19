import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { WeekMenuComponentsPage, WeekMenuDeleteDialog, WeekMenuUpdatePage } from './week-menu.page-object';

const expect = chai.expect;

describe('WeekMenu e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let weekMenuComponentsPage: WeekMenuComponentsPage;
  let weekMenuUpdatePage: WeekMenuUpdatePage;
  let weekMenuDeleteDialog: WeekMenuDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load WeekMenus', async () => {
    await navBarPage.goToEntity('week-menu');
    weekMenuComponentsPage = new WeekMenuComponentsPage();
    await browser.wait(ec.visibilityOf(weekMenuComponentsPage.title), 5000);
    expect(await weekMenuComponentsPage.getTitle()).to.eq('jhApp.weekMenu.home.title');
    await browser.wait(ec.or(ec.visibilityOf(weekMenuComponentsPage.entities), ec.visibilityOf(weekMenuComponentsPage.noResult)), 1000);
  });

  it('should load create WeekMenu page', async () => {
    await weekMenuComponentsPage.clickOnCreateButton();
    weekMenuUpdatePage = new WeekMenuUpdatePage();
    expect(await weekMenuUpdatePage.getPageTitle()).to.eq('jhApp.weekMenu.home.createOrEditLabel');
    await weekMenuUpdatePage.cancel();
  });

  it('should create and save WeekMenus', async () => {
    const nbButtonsBeforeCreate = await weekMenuComponentsPage.countDeleteButtons();

    await weekMenuComponentsPage.clickOnCreateButton();

    await promise.all([
      weekMenuUpdatePage.weekSelectLastOption(),
      weekMenuUpdatePage.setSortInput('5'),
      weekMenuUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      weekMenuUpdatePage.setLastModifiedByInput('lastModifiedBy'),
      weekMenuUpdatePage.foodSelectLastOption()
    ]);

    expect(await weekMenuUpdatePage.getSortInput()).to.eq('5', 'Expected sort value to be equals to 5');
    expect(await weekMenuUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await weekMenuUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await weekMenuUpdatePage.save();
    expect(await weekMenuUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await weekMenuComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last WeekMenu', async () => {
    const nbButtonsBeforeDelete = await weekMenuComponentsPage.countDeleteButtons();
    await weekMenuComponentsPage.clickOnLastDeleteButton();

    weekMenuDeleteDialog = new WeekMenuDeleteDialog();
    expect(await weekMenuDeleteDialog.getDialogTitle()).to.eq('jhApp.weekMenu.delete.question');
    await weekMenuDeleteDialog.clickOnConfirmButton();

    expect(await weekMenuComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
