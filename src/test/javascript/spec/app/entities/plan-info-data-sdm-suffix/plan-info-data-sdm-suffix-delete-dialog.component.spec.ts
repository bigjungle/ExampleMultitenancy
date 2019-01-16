/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix-delete-dialog.component';
import { PlanInfoDataSdmSuffixService } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoDataSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoDataSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoDataSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoDataSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoDataSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataSdmSuffixService);
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
