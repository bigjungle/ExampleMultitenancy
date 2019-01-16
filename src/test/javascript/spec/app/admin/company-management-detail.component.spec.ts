/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../test.module';
import { CompanyMgmtDetailComponent } from 'app/admin/company-management/company-management-detail.component';
import { Company } from 'app/admin/company-management/company.model';

describe('Component Tests', () => {
    describe('Company Management Detail Component', () => {
        let comp: CompanyMgmtDetailComponent;
        let fixture: ComponentFixture<CompanyMgmtDetailComponent>;
        const route = ({
            data: of({ company: new Company(1, 'mycompany') })
        } as any) as ActivatedRoute;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [CompanyMgmtDetailComponent],
                providers: [
                    {
                        provide: ActivatedRoute,
                        useValue: route
                    }
                ]
            })
                .overrideTemplate(CompanyMgmtDetailComponent, '')
                .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CompanyMgmtDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.company).toEqual(
                    jasmine.objectContaining({
                        id: 1,
                        name: 'mycompany'
                    })
                );
            });
        });
    });
});
