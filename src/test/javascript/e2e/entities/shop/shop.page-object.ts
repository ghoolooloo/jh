import { element, by, ElementFinder } from 'protractor';

export class ShopComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-shop div table .btn-danger'));
  title = element.all(by.css('jhi-shop div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ShopUpdatePage {
  pageTitle = element(by.id('jhi-shop-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  snInput = element(by.id('field_sn'));
  telInput = element(by.id('field_tel'));
  addressInput = element(by.id('field_address'));
  provinceInput = element(by.id('field_province'));
  cityInput = element(by.id('field_city'));
  districtInput = element(by.id('field_district'));
  offsetTypeSelect = element(by.id('field_offsetType'));
  longitudeInput = element(by.id('field_longitude'));
  latitudeInput = element(by.id('field_latitude'));
  shopOpenInput = element(by.id('field_shopOpen'));
  shopCloseInput = element(by.id('field_shopClose'));
  maxDeliveryDistanceInput = element(by.id('field_maxDeliveryDistance'));
  minDeliveryAmountInput = element(by.id('field_minDeliveryAmount'));
  lunchServeStartingAtInput = element(by.id('field_lunchServeStartingAt'));
  lunchServeEndAtInput = element(by.id('field_lunchServeEndAt'));
  supperServeStartingAtInput = element(by.id('field_supperServeStartingAt'));
  supperServeEndAtInput = element(by.id('field_supperServeEndAt'));
  createdByInput = element(by.id('field_createdBy'));
  createdDateInput = element(by.id('field_createdDate'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));
  lastModifiedByInput = element(by.id('field_lastModifiedBy'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setSnInput(sn: string): Promise<void> {
    await this.snInput.sendKeys(sn);
  }

  async getSnInput(): Promise<string> {
    return await this.snInput.getAttribute('value');
  }

  async setTelInput(tel: string): Promise<void> {
    await this.telInput.sendKeys(tel);
  }

  async getTelInput(): Promise<string> {
    return await this.telInput.getAttribute('value');
  }

  async setAddressInput(address: string): Promise<void> {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput(): Promise<string> {
    return await this.addressInput.getAttribute('value');
  }

  async setProvinceInput(province: string): Promise<void> {
    await this.provinceInput.sendKeys(province);
  }

  async getProvinceInput(): Promise<string> {
    return await this.provinceInput.getAttribute('value');
  }

  async setCityInput(city: string): Promise<void> {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput(): Promise<string> {
    return await this.cityInput.getAttribute('value');
  }

  async setDistrictInput(district: string): Promise<void> {
    await this.districtInput.sendKeys(district);
  }

  async getDistrictInput(): Promise<string> {
    return await this.districtInput.getAttribute('value');
  }

  async setOffsetTypeSelect(offsetType: string): Promise<void> {
    await this.offsetTypeSelect.sendKeys(offsetType);
  }

  async getOffsetTypeSelect(): Promise<string> {
    return await this.offsetTypeSelect.element(by.css('option:checked')).getText();
  }

  async offsetTypeSelectLastOption(): Promise<void> {
    await this.offsetTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setLongitudeInput(longitude: string): Promise<void> {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput(): Promise<string> {
    return await this.longitudeInput.getAttribute('value');
  }

  async setLatitudeInput(latitude: string): Promise<void> {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput(): Promise<string> {
    return await this.latitudeInput.getAttribute('value');
  }

  async setShopOpenInput(shopOpen: string): Promise<void> {
    await this.shopOpenInput.sendKeys(shopOpen);
  }

  async getShopOpenInput(): Promise<string> {
    return await this.shopOpenInput.getAttribute('value');
  }

  async setShopCloseInput(shopClose: string): Promise<void> {
    await this.shopCloseInput.sendKeys(shopClose);
  }

  async getShopCloseInput(): Promise<string> {
    return await this.shopCloseInput.getAttribute('value');
  }

  async setMaxDeliveryDistanceInput(maxDeliveryDistance: string): Promise<void> {
    await this.maxDeliveryDistanceInput.sendKeys(maxDeliveryDistance);
  }

  async getMaxDeliveryDistanceInput(): Promise<string> {
    return await this.maxDeliveryDistanceInput.getAttribute('value');
  }

  async setMinDeliveryAmountInput(minDeliveryAmount: string): Promise<void> {
    await this.minDeliveryAmountInput.sendKeys(minDeliveryAmount);
  }

  async getMinDeliveryAmountInput(): Promise<string> {
    return await this.minDeliveryAmountInput.getAttribute('value');
  }

  async setLunchServeStartingAtInput(lunchServeStartingAt: string): Promise<void> {
    await this.lunchServeStartingAtInput.sendKeys(lunchServeStartingAt);
  }

  async getLunchServeStartingAtInput(): Promise<string> {
    return await this.lunchServeStartingAtInput.getAttribute('value');
  }

  async setLunchServeEndAtInput(lunchServeEndAt: string): Promise<void> {
    await this.lunchServeEndAtInput.sendKeys(lunchServeEndAt);
  }

  async getLunchServeEndAtInput(): Promise<string> {
    return await this.lunchServeEndAtInput.getAttribute('value');
  }

  async setSupperServeStartingAtInput(supperServeStartingAt: string): Promise<void> {
    await this.supperServeStartingAtInput.sendKeys(supperServeStartingAt);
  }

  async getSupperServeStartingAtInput(): Promise<string> {
    return await this.supperServeStartingAtInput.getAttribute('value');
  }

  async setSupperServeEndAtInput(supperServeEndAt: string): Promise<void> {
    await this.supperServeEndAtInput.sendKeys(supperServeEndAt);
  }

  async getSupperServeEndAtInput(): Promise<string> {
    return await this.supperServeEndAtInput.getAttribute('value');
  }

  async setCreatedByInput(createdBy: string): Promise<void> {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput(): Promise<string> {
    return await this.createdByInput.getAttribute('value');
  }

  async setCreatedDateInput(createdDate: string): Promise<void> {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput(): Promise<string> {
    return await this.createdDateInput.getAttribute('value');
  }

  async setLastModifiedDateInput(lastModifiedDate: string): Promise<void> {
    await this.lastModifiedDateInput.sendKeys(lastModifiedDate);
  }

  async getLastModifiedDateInput(): Promise<string> {
    return await this.lastModifiedDateInput.getAttribute('value');
  }

  async setLastModifiedByInput(lastModifiedBy: string): Promise<void> {
    await this.lastModifiedByInput.sendKeys(lastModifiedBy);
  }

  async getLastModifiedByInput(): Promise<string> {
    return await this.lastModifiedByInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ShopDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-shop-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-shop'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
