import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhSharedModule } from 'app/shared/shared.module';
import { FoodCategoryComponent } from './food-category.component';
import { FoodCategoryDetailComponent } from './food-category-detail.component';
import { FoodCategoryUpdateComponent } from './food-category-update.component';
import { FoodCategoryDeleteDialogComponent } from './food-category-delete-dialog.component';
import { foodCategoryRoute } from './food-category.route';
import { NzUploadModule } from 'ng-zorro-antd/upload';

@NgModule({
  imports: [JhSharedModule, RouterModule.forChild(foodCategoryRoute), NzUploadModule],
  declarations: [FoodCategoryComponent, FoodCategoryDetailComponent, FoodCategoryUpdateComponent, FoodCategoryDeleteDialogComponent],
  entryComponents: [FoodCategoryDeleteDialogComponent]
})
export class JhFoodCategoryModule {}
