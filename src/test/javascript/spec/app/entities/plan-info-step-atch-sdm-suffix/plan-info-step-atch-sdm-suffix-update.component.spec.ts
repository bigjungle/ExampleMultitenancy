/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepAtchSdmSuffixUpdateComponent } from 'app/entities/plan-info-step-atch-sdm-suffix/plan-info-step-atch-sdm-suffix-update.component';
import { PlanInfoStepAtchSdmSuffixService } from 'app/entities/plan-info-step-atch-sdm-suffix/plan-info-step-atch-sdm-suffix.service';
import { PlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepAtchSdmSuffix Management Update Component', () => {
        let comp: PlanInfoStepAtchSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoStepAtchSdmSuffixUpdateComponent>;
        let service: PlanInfoStepAtchSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepAtchSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoStepAtchSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoStepAtchSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepAtchSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepAtchSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepAtch = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepAtchSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepAtch = entity;
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
