import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhTestModule } from '../../../test.module';
import { FoodCategoryDetailComponent } from 'app/entities/food-category/food-category-detail.component';
import { FoodCategory } from 'app/shared/model/food-category.model';

describe('Component Tests', () => {
  describe('FoodCategory Management Detail Component', () => {
    let comp: FoodCategoryDetailComponent;
    let fixture: ComponentFixture<FoodCategoryDetailComponent>;
    const route = ({ data: of({ foodCategory: new FoodCategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [FoodCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FoodCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FoodCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load foodCategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.foodCategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
