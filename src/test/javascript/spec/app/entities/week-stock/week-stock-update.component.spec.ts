import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhTestModule } from '../../../test.module';
import { WeekStockUpdateComponent } from 'app/entities/week-stock/week-stock-update.component';
import { WeekStockService } from 'app/entities/week-stock/week-stock.service';
import { WeekStock } from 'app/shared/model/week-stock.model';

describe('Component Tests', () => {
  describe('WeekStock Management Update Component', () => {
    let comp: WeekStockUpdateComponent;
    let fixture: ComponentFixture<WeekStockUpdateComponent>;
    let service: WeekStockService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [WeekStockUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WeekStockUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeekStockUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeekStockService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WeekStock(123);
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
        const entity = new WeekStock();
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
