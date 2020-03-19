import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFoodCategory, FoodCategory } from 'app/shared/model/food-category.model';
import { FoodCategoryService } from './food-category.service';
import { FoodCategoryComponent } from './food-category.component';
import { FoodCategoryDetailComponent } from './food-category-detail.component';
import { FoodCategoryUpdateComponent } from './food-category-update.component';

@Injectable({ providedIn: 'root' })
export class FoodCategoryResolve implements Resolve<IFoodCategory> {
  constructor(private service: FoodCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFoodCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((foodCategory: HttpResponse<FoodCategory>) => {
          if (foodCategory.body) {
            return of(foodCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FoodCategory());
  }
}

export const foodCategoryRoute: Routes = [
  {
    path: '',
    component: FoodCategoryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.foodCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FoodCategoryDetailComponent,
    resolve: {
      foodCategory: FoodCategoryResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.foodCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FoodCategoryUpdateComponent,
    resolve: {
      foodCategory: FoodCategoryResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.foodCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FoodCategoryUpdateComponent,
    resolve: {
      foodCategory: FoodCategoryResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.foodCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
