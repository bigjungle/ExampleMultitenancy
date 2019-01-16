/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsDepSdmSuffixUpdateComponent } from 'app/entities/rms-dep-sdm-suffix/rms-dep-sdm-suffix-update.component';
import { RmsDepSdmSuffixService } from 'app/entities/rms-dep-sdm-suffix/rms-dep-sdm-suffix.service';
import { RmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsDepSdmSuffix Management Update Component', () => {
        let comp: RmsDepSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<RmsDepSdmSuffixUpdateComponent>;
        let service: RmsDepSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsDepSdmSuffixUpdateComponent]
            })
                .overrideTemplate(RmsDepSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RmsDepSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsDepSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsDepSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsDep = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsDepSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsDep = entity;
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
