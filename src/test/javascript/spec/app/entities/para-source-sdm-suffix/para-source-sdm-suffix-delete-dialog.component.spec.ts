/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { ParaSourceSdmSuffixDeleteDialogComponent } from 'app/entities/para-source-sdm-suffix/para-source-sdm-suffix-delete-dialog.component';
import { ParaSourceSdmSuffixService } from 'app/entities/para-source-sdm-suffix/para-source-sdm-suffix.service';

describe('Component Tests', () => {
    describe('ParaSourceSdmSuffix Management Delete Component', () => {
        let comp: ParaSourceSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ParaSourceSdmSuffixDeleteDialogComponent>;
        let service: ParaSourceSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaSourceSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(ParaSourceSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaSourceSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaSourceSdmSuffixService);
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
