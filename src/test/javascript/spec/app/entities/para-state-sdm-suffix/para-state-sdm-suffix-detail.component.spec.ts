/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaStateSdmSuffixDetailComponent } from 'app/entities/para-state-sdm-suffix/para-state-sdm-suffix-detail.component';
import { ParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaStateSdmSuffix Management Detail Component', () => {
        let comp: ParaStateSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaStateSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraState: new ParaStateSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaStateSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaStateSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaStateSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraState).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
