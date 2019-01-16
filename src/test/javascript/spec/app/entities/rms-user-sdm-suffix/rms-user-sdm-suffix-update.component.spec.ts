/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsUserSdmSuffixUpdateComponent } from 'app/entities/rms-user-sdm-suffix/rms-user-sdm-suffix-update.component';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix/rms-user-sdm-suffix.service';
import { RmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsUserSdmSuffix Management Update Component', () => {
        let comp: RmsUserSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<RmsUserSdmSuffixUpdateComponent>;
        let service: RmsUserSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsUserSdmSuffixUpdateComponent]
            })
                .overrideTemplate(RmsUserSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RmsUserSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsUserSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsUserSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsUserSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsUser = entity;
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
