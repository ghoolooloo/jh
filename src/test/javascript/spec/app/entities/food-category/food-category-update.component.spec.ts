import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhTestModule } from '../../../test.module';
import { FoodCategoryUpdateComponent } from 'app/entities/food-category/food-category-update.component';
import { FoodCategoryService } from 'app/entities/food-category/food-category.service';
import { FoodCategory } from 'app/shared/model/food-category.model';

describe('Component Tests', () => {
  describe('FoodCategory Management Update Component', () => {
    let comp: FoodCategoryUpdateComponent;
    let fixture: ComponentFixture<FoodCategoryUpdateComponent>;
    let service: FoodCategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [FoodCategoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FoodCategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FoodCategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FoodCategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FoodCategory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FoodCategory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
