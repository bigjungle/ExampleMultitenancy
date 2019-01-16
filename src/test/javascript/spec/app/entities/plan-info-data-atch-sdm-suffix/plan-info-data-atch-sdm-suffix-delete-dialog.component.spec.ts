/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataAtchSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-data-atch-sdm-suffix/plan-info-data-atch-sdm-suffix-delete-dialog.component';
import { PlanInfoDataAtchSdmSuffixService } from 'app/entities/plan-info-data-atch-sdm-suffix/plan-info-data-atch-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoDataAtchSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoDataAtchSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoDataAtchSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoDataAtchSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataAtchSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoDataAtchSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataAtchSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataAtchSdmSuffixService);
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
