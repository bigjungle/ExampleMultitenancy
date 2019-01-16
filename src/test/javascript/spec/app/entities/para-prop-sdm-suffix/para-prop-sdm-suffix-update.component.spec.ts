/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaPropSdmSuffixUpdateComponent } from 'app/entities/para-prop-sdm-suffix/para-prop-sdm-suffix-update.component';
import { ParaPropSdmSuffixService } from 'app/entities/para-prop-sdm-suffix/para-prop-sdm-suffix.service';
import { ParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaPropSdmSuffix Management Update Component', () => {
        let comp: ParaPropSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaPropSdmSuffixUpdateComponent>;
        let service: ParaPropSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaPropSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaPropSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaPropSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaPropSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaPropSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraProp = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaPropSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraProp = entity;
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
