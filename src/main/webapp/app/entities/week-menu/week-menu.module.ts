import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhSharedModule } from 'app/shared/shared.module';
import { WeekMenuComponent } from './week-menu.component';
import { WeekMenuDetailComponent } from './week-menu-detail.component';
import { WeekMenuUpdateComponent } from './week-menu-update.component';
import { WeekMenuDeleteDialogComponent } from './week-menu-delete-dialog.component';
import { weekMenuRoute } from './week-menu.route';

@NgModule({
  imports: [JhSharedModule, RouterModule.forChild(weekMenuRoute)],
  declarations: [WeekMenuComponent, WeekMenuDetailComponent, WeekMenuUpdateComponent, WeekMenuDeleteDialogComponent],
  entryComponents: [WeekMenuDeleteDialogComponent]
})
export class JhWeekMenuModule {}
