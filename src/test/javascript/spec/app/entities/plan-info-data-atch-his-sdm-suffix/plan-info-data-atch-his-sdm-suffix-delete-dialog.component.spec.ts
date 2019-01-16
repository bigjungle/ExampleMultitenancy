/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-data-atch-his-sdm-suffix/plan-info-data-atch-his-sdm-suffix-delete-dialog.component';
import { PlanInfoDataAtchHisSdmSuffixService } from 'app/entities/plan-info-data-atch-his-sdm-suffix/plan-info-data-atch-his-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoDataAtchHisSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoDataAtchHisSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataAtchHisSdmSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
