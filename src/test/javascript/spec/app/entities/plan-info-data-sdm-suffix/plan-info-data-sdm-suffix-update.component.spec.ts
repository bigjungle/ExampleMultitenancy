/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataSdmSuffixUpdateComponent } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix-update.component';
import { PlanInfoDataSdmSuffixService } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix.service';
import { PlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataSdmSuffix Management Update Component', () => {
        let comp: PlanInfoDataSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoDataSdmSuffixUpdateComponent>;
        let service: PlanInfoDataSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoDataSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoDataSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoData = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoData = entity;
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
