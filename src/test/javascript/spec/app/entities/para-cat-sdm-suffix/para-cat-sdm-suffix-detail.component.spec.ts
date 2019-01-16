/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaCatSdmSuffixDetailComponent } from 'app/entities/para-cat-sdm-suffix/para-cat-sdm-suffix-detail.component';
import { ParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaCatSdmSuffix Management Detail Component', () => {
        let comp: ParaCatSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaCatSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraCat: new ParaCatSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaCatSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaCatSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaCatSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraCat).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
