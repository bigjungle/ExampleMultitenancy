/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataHisSdmSuffixUpdateComponent } from 'app/entities/plan-info-data-his-sdm-suffix/plan-info-data-his-sdm-suffix-update.component';
import { PlanInfoDataHisSdmSuffixService } from 'app/entities/plan-info-data-his-sdm-suffix/plan-info-data-his-sdm-suffix.service';
import { PlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataHisSdmSuffix Management Update Component', () => {
        let comp: PlanInfoDataHisSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoDataHisSdmSuffixUpdateComponent>;
        let service: PlanInfoDataHisSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataHisSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoDataHisSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoDataHisSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataHisSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataHisSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoDataHis = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataHisSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoDataHis = entity;
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
