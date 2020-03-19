import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'food-category',
        loadChildren: () => import('./food-category/food-category.module').then(m => m.JhFoodCategoryModule)
      },
      {
        path: 'food',
        loadChildren: () => import('./food/food.module').then(m => m.JhFoodModule)
      },
      {
        path: 'week-menu',
        loadChildren: () => import('./week-menu/week-menu.module').then(m => m.JhWeekMenuModule)
      },
      {
        path: 'week-stock',
        loadChildren: () => import('./week-stock/week-stock.module').then(m => m.JhWeekStockModule)
      },
      {
        path: 'shop',
        loadChildren: () => import('./shop/shop.module').then(m => m.JhShopModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhEntityModule {}
