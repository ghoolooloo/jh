import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ShopComponentsPage, ShopDeleteDialog, ShopUpdatePage } from './shop.page-object';

const expect = chai.expect;

describe('Shop e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let shopComponentsPage: ShopComponentsPage;
  let shopUpdatePage: ShopUpdatePage;
  let shopDeleteDialog: ShopDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Shops', async () => {
    await navBarPage.goToEntity('shop');
    shopComponentsPage = new ShopComponentsPage();
    await browser.wait(ec.visibilityOf(shopComponentsPage.title), 5000);
    expect(await shopComponentsPage.getTitle()).to.eq('jhApp.shop.home.title');
    await browser.wait(ec.or(ec.visibilityOf(shopComponentsPage.entities), ec.visibilityOf(shopComponentsPage.noResult)), 1000);
  });

  it('should load create Shop page', async () => {
    await shopComponentsPage.clickOnCreateButton();
    shopUpdatePage = new ShopUpdatePage();
    expect(await shopUpdatePage.getPageTitle()).to.eq('jhApp.shop.home.createOrEditLabel');
    await shopUpdatePage.cancel();
  });

  it('should create and save Shops', async () => {
    const nbButtonsBeforeCreate = await shopComponentsPage.countDeleteButtons();

    await shopComponentsPage.clickOnCreateButton();

    await promise.all([
      shopUpdatePage.setNameInput('name'),
      shopUpdatePage.setSnInput('sn'),
      shopUpdatePage.setTelInput('tel'),
      shopUpdatePage.setAddressInput('address'),
      shopUpdatePage.setProvinceInput('province'),
      shopUpdatePage.setCityInput('city'),
      shopUpdatePage.setDistrictInput('district'),
      shopUpdatePage.offsetTypeSelectLastOption(),
      shopUpdatePage.setLongitudeInput('5'),
      shopUpdatePage.setLatitudeInput('5'),
      shopUpdatePage.setShopOpenInput('shopOpen'),
      shopUpdatePage.setShopCloseInput('shopClose'),
      shopUpdatePage.setMaxDeliveryDistanceInput('5'),
      shopUpdatePage.setMinDeliveryAmountInput('5'),
      shopUpdatePage.setLunchServeStartingAtInput('lunchServeStartingAt'),
      shopUpdatePage.setLunchServeEndAtInput('lunchServeEndAt'),
      shopUpdatePage.setSupperServeStartingAtInput('supperServeStartingAt'),
      shopUpdatePage.setSupperServeEndAtInput('supperServeEndAt'),
      shopUpdatePage.setCreatedByInput('createdBy'),
      shopUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      shopUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      shopUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await shopUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await shopUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await shopUpdatePage.getTelInput()).to.eq('tel', 'Expected Tel value to be equals to tel');
    expect(await shopUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await shopUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await shopUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await shopUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await shopUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await shopUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await shopUpdatePage.getShopOpenInput()).to.eq('shopOpen', 'Expected ShopOpen value to be equals to shopOpen');
    expect(await shopUpdatePage.getShopCloseInput()).to.eq('shopClose', 'Expected ShopClose value to be equals to shopClose');
    expect(await shopUpdatePage.getMaxDeliveryDistanceInput()).to.eq('5', 'Expected maxDeliveryDistance value to be equals to 5');
    expect(await shopUpdatePage.getMinDeliveryAmountInput()).to.eq('5', 'Expected minDeliveryAmount value to be equals to 5');
    expect(await shopUpdatePage.getLunchServeStartingAtInput()).to.eq(
      'lunchServeStartingAt',
      'Expected LunchServeStartingAt value to be equals to lunchServeStartingAt'
    );
    expect(await shopUpdatePage.getLunchServeEndAtInput()).to.eq(
      'lunchServeEndAt',
      'Expected LunchServeEndAt value to be equals to lunchServeEndAt'
    );
    expect(await shopUpdatePage.getSupperServeStartingAtInput()).to.eq(
      'supperServeStartingAt',
      'Expected SupperServeStartingAt value to be equals to supperServeStartingAt'
    );
    expect(await shopUpdatePage.getSupperServeEndAtInput()).to.eq(
      'supperServeEndAt',
      'Expected SupperServeEndAt value to be equals to supperServeEndAt'
    );
    expect(await shopUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await shopUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await shopUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await shopUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await shopUpdatePage.save();
    expect(await shopUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await shopComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Shop', async () => {
    const nbButtonsBeforeDelete = await shopComponentsPage.countDeleteButtons();
    await shopComponentsPage.clickOnLastDeleteButton();

    shopDeleteDialog = new ShopDeleteDialog();
    expect(await shopDeleteDialog.getDialogTitle()).to.eq('jhApp.shop.delete.question');
    await shopDeleteDialog.clickOnConfirmButton();

    expect(await shopComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
