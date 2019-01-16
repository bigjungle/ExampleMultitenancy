import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoStepDataAtchHisSdmSuffixComponent,
    PlanInfoStepDataAtchHisSdmSuffixDetailComponent,
    PlanInfoStepDataAtchHisSdmSuffixUpdateComponent,
    PlanInfoStepDataAtchHisSdmSuffixDeletePopupComponent,
    PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent,
    planInfoStepDataAtchHisRoute,
    planInfoStepDataAtchHisPopupRoute
} from './';

const ENTITY_STATES = [...planInfoStepDataAtchHisRoute, ...planInfoStepDataAtchHisPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoStepDataAtchHisSdmSuffixComponent,
        PlanInfoStepDataAtchHisSdmSuffixDetailComponent,
        PlanInfoStepDataAtchHisSdmSuffixUpdateComponent,
        PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataAtchHisSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoStepDataAtchHisSdmSuffixComponent,
        PlanInfoStepDataAtchHisSdmSuffixUpdateComponent,
        PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataAtchHisSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoStepDataAtchHisSdmSuffixModule {}
