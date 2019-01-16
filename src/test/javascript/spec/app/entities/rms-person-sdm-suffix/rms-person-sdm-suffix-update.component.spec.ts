/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsPersonSdmSuffixUpdateComponent } from 'app/entities/rms-person-sdm-suffix/rms-person-sdm-suffix-update.component';
import { RmsPersonSdmSuffixService } from 'app/entities/rms-person-sdm-suffix/rms-person-sdm-suffix.service';
import { RmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsPersonSdmSuffix Management Update Component', () => {
        let comp: RmsPersonSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<RmsPersonSdmSuffixUpdateComponent>;
        let service: RmsPersonSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsPersonSdmSuffixUpdateComponent]
            })
                .overrideTemplate(RmsPersonSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RmsPersonSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsPersonSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsPersonSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsPerson = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsPersonSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsPerson = entity;
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
