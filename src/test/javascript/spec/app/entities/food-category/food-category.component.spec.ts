import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhTestModule } from '../../../test.module';
import { FoodCategoryComponent } from 'app/entities/food-category/food-category.component';
import { FoodCategoryService } from 'app/entities/food-category/food-category.service';
import { FoodCategory } from 'app/shared/model/food-category.model';

describe('Component Tests', () => {
  describe('FoodCategory Management Component', () => {
    let comp: FoodCategoryComponent;
    let fixture: ComponentFixture<FoodCategoryComponent>;
    let service: FoodCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [FoodCategoryComponent]
      })
        .overrideTemplate(FoodCategoryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FoodCategoryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FoodCategoryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FoodCategory(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.foodCategories && comp.foodCategories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
