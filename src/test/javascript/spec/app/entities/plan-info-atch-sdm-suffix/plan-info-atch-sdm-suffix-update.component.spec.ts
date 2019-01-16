/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoAtchSdmSuffixUpdateComponent } from 'app/entities/plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix-update.component';
import { PlanInfoAtchSdmSuffixService } from 'app/entities/plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix.service';
import { PlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoAtchSdmSuffix Management Update Component', () => {
        let comp: PlanInfoAtchSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoAtchSdmSuffixUpdateComponent>;
        let service: PlanInfoAtchSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoAtchSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoAtchSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoAtchSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoAtchSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoAtchSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoAtch = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoAtchSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoAtch = entity;
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
