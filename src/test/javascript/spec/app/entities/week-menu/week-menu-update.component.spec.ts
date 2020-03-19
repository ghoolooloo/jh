import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhTestModule } from '../../../test.module';
import { WeekMenuUpdateComponent } from 'app/entities/week-menu/week-menu-update.component';
import { WeekMenuService } from 'app/entities/week-menu/week-menu.service';
import { WeekMenu } from 'app/shared/model/week-menu.model';

describe('Component Tests', () => {
  describe('WeekMenu Management Update Component', () => {
    let comp: WeekMenuUpdateComponent;
    let fixture: ComponentFixture<WeekMenuUpdateComponent>;
    let service: WeekMenuService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [WeekMenuUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WeekMenuUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeekMenuUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeekMenuService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WeekMenu(123);
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
        const entity = new WeekMenu();
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
