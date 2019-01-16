/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaTypeSdmSuffixUpdateComponent } from 'app/entities/para-type-sdm-suffix/para-type-sdm-suffix-update.component';
import { ParaTypeSdmSuffixService } from 'app/entities/para-type-sdm-suffix/para-type-sdm-suffix.service';
import { ParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaTypeSdmSuffix Management Update Component', () => {
        let comp: ParaTypeSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaTypeSdmSuffixUpdateComponent>;
        let service: ParaTypeSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaTypeSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaTypeSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaTypeSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaTypeSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaTypeSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraType = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaTypeSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraType = entity;
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
