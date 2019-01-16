/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaTypeSdmSuffixDetailComponent } from 'app/entities/para-type-sdm-suffix/para-type-sdm-suffix-detail.component';
import { ParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaTypeSdmSuffix Management Detail Component', () => {
        let comp: ParaTypeSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaTypeSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraType: new ParaTypeSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaTypeSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaTypeSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaTypeSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
