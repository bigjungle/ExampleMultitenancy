/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PlanInfoAtchSdmSuffixService } from 'app/entities/plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix.service';
import { IPlanInfoAtchSdmSuffix, PlanInfoAtchSdmSuffix, ValidType } from 'app/shared/model/plan-info-atch-sdm-suffix.model';

describe('Service Tests', () => {
    describe('PlanInfoAtchSdmSuffix Service', () => {
        let injector: TestBed;
        let service: PlanInfoAtchSdmSuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IPlanInfoAtchSdmSuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PlanInfoAtchSdmSuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new PlanInfoAtchSdmSuffix(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'AAAAAAA',
                ValidType.LONG,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a PlanInfoAtchSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        publishedTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new PlanInfoAtchSdmSuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a PlanInfoAtchSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        storeDir: 'BBBBBB',
                        storeName: 'BBBBBB',
                        sortString: 'BBBBBB',
                        fileType: 'BBBBBB',
                        deleteFlag: true,
                        storeType: 'BBBBBB',
                        ver: 'BBBBBB',
                        encryptedFlag: 'BBBBBB',
                        encryptedType: 'BBBBBB',
                        jsonString: 'BBBBBB',
                        remarks: 'BBBBBB',
                        attachmentPath: 'BBBBBB',
                        attachmentBlob: 'BBBBBB',
                        attachmentName: 'BBBBBB',
                        textBlob: 'BBBBBB',
                        imageBlob: 'BBBBBB',
                        imageBlobName: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        publishedTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of PlanInfoAtchSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        storeDir: 'BBBBBB',
                        storeName: 'BBBBBB',
                        sortString: 'BBBBBB',
                        fileType: 'BBBBBB',
                        deleteFlag: true,
                        storeType: 'BBBBBB',
                        ver: 'BBBBBB',
                        encryptedFlag: 'BBBBBB',
                        encryptedType: 'BBBBBB',
                        jsonString: 'BBBBBB',
                        remarks: 'BBBBBB',
                        attachmentPath: 'BBBBBB',
                        attachmentBlob: 'BBBBBB',
                        attachmentName: 'BBBBBB',
                        textBlob: 'BBBBBB',
                        imageBlob: 'BBBBBB',
                        imageBlobName: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        publishedTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a PlanInfoAtchSdmSuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
