/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepSdmSuffixUpdateComponent } from 'app/entities/plan-info-step-sdm-suffix/plan-info-step-sdm-suffix-update.component';
import { PlanInfoStepSdmSuffixService } from 'app/entities/plan-info-step-sdm-suffix/plan-info-step-sdm-suffix.service';
import { PlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepSdmSuffix Management Update Component', () => {
        let comp: PlanInfoStepSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoStepSdmSuffixUpdateComponent>;
        let service: PlanInfoStepSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoStepSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoStepSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStep = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStep = entity;
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
