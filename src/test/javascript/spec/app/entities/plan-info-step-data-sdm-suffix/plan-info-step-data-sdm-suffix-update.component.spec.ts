/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataSdmSuffixUpdateComponent } from 'app/entities/plan-info-step-data-sdm-suffix/plan-info-step-data-sdm-suffix-update.component';
import { PlanInfoStepDataSdmSuffixService } from 'app/entities/plan-info-step-data-sdm-suffix/plan-info-step-data-sdm-suffix.service';
import { PlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataSdmSuffix Management Update Component', () => {
        let comp: PlanInfoStepDataSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoStepDataSdmSuffixUpdateComponent>;
        let service: PlanInfoStepDataSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoStepDataSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoStepDataSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepData = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepData = entity;
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
