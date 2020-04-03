import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/camelcase
import { NZ_I18N, zh_CN } from 'ng-zorro-antd';

import './vendor';
import { JhSharedModule } from 'app/shared/shared.module';
import { JhCoreModule } from 'app/core/core.module';
import { JhAppRoutingModule } from './app-routing.module';
import { JhHomeModule } from './home/home.module';
import { JhEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

/** 配置 angular i18n **/
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
registerLocaleData(zh);

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    JhSharedModule,
    JhCoreModule,
    JhHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    JhEntityModule,
    JhAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
  /** 配置 ng-zorro-antd 国际化（文案 及 日期） **/
  providers: [
    // eslint-disable-next-line @typescript-eslint/camelcase
    { provide: NZ_I18N, useValue: zh_CN }
  ]
})
export class JhAppModule {}
