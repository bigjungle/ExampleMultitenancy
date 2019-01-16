/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaAttrSdmSuffixDetailComponent } from 'app/entities/para-attr-sdm-suffix/para-attr-sdm-suffix-detail.component';
import { ParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaAttrSdmSuffix Management Detail Component', () => {
        let comp: ParaAttrSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaAttrSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraAttr: new ParaAttrSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaAttrSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaAttrSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaAttrSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraAttr).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
