import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaSourceSdmSuffixComponent,
    ParaSourceSdmSuffixDetailComponent,
    ParaSourceSdmSuffixUpdateComponent,
    ParaSourceSdmSuffixDeletePopupComponent,
    ParaSourceSdmSuffixDeleteDialogComponent,
    paraSourceRoute,
    paraSourcePopupRoute
} from './';

const ENTITY_STATES = [...paraSourceRoute, ...paraSourcePopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaSourceSdmSuffixComponent,
        ParaSourceSdmSuffixDetailComponent,
        ParaSourceSdmSuffixUpdateComponent,
        ParaSourceSdmSuffixDeleteDialogComponent,
        ParaSourceSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaSourceSdmSuffixComponent,
        ParaSourceSdmSuffixUpdateComponent,
        ParaSourceSdmSuffixDeleteDialogComponent,
        ParaSourceSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaSourceSdmSuffixModule {}
