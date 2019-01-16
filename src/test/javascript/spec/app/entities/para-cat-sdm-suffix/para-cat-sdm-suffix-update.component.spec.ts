/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaCatSdmSuffixUpdateComponent } from 'app/entities/para-cat-sdm-suffix/para-cat-sdm-suffix-update.component';
import { ParaCatSdmSuffixService } from 'app/entities/para-cat-sdm-suffix/para-cat-sdm-suffix.service';
import { ParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaCatSdmSuffix Management Update Component', () => {
        let comp: ParaCatSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaCatSdmSuffixUpdateComponent>;
        let service: ParaCatSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaCatSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaCatSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaCatSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaCatSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaCatSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraCat = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaCatSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraCat = entity;
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
