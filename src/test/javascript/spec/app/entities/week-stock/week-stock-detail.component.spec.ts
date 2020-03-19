import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhTestModule } from '../../../test.module';
import { WeekStockDetailComponent } from 'app/entities/week-stock/week-stock-detail.component';
import { WeekStock } from 'app/shared/model/week-stock.model';

describe('Component Tests', () => {
  describe('WeekStock Management Detail Component', () => {
    let comp: WeekStockDetailComponent;
    let fixture: ComponentFixture<WeekStockDetailComponent>;
    const route = ({ data: of({ weekStock: new WeekStock(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [WeekStockDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WeekStockDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WeekStockDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load weekStock on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.weekStock).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
