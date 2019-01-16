import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoStepDataSdmSuffixComponent,
    PlanInfoStepDataSdmSuffixDetailComponent,
    PlanInfoStepDataSdmSuffixUpdateComponent,
    PlanInfoStepDataSdmSuffixDeletePopupComponent,
    PlanInfoStepDataSdmSuffixDeleteDialogComponent,
    planInfoStepDataRoute,
    planInfoStepDataPopupRoute
} from './';

const ENTITY_STATES = [...planInfoStepDataRoute, ...planInfoStepDataPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoStepDataSdmSuffixComponent,
        PlanInfoStepDataSdmSuffixDetailComponent,
        PlanInfoStepDataSdmSuffixUpdateComponent,
        PlanInfoStepDataSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoStepDataSdmSuffixComponent,
        PlanInfoStepDataSdmSuffixUpdateComponent,
        PlanInfoStepDataSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoStepDataSdmSuffixModule {}
