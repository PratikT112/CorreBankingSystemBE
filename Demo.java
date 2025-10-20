{
	"info": {
		"_postman_id": "818f4dac-feee-442a-9719-8fd20904f1c6",
		"name": "CBS",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "16289804"
	},
	"item": [
		{
			"name": "CMOB",
			"item": [
				{
					"name": "NEW CMOB",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									""
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "[\r\n    {\r\n        \"id\": {\r\n            \"socNo\": \"003\",\r\n            \"custNo\": \"0000008526012352\",\r\n            \"identifier\": \"T\"\r\n        },\r\n        \"custMobNo\": \"9322880598\",\r\n        \"isdCode\": \"91\",\r\n        \"mbexExpDt\": \"18082025\",\r\n        // \"oldCustMobNo\": \"9930473061\",\r\n        // \"oldMobIsdCode\": \"91\",\r\n        \"chnlId\": \" \",\r\n        \"makerId\": \"2199690\",\r\n        \"checkerId\": \"2199691\"\r\n    },\r\n    {\r\n        \"id\": {\r\n            \"socNo\": \"003\",\r\n            \"custNo\": \"0000008526012352\",\r\n            \"identifier\": \"P\"\r\n        },\r\n        \"custMobNo\": \"7506111495\",\r\n        \"isdCode\": \"91\",\r\n        // \"oldCustMobNo\": \"\",\r\n        // \"oldMobIsdCode\": \"\",\r\n        \"chnlId\": \" \",\r\n        \"makerId\": \"2199690\",\r\n        \"checkerId\": \"2199691\"\r\n    }\r\n]",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/cmob/new",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cmob",
								"new"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get OCOM",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/ocom/003/0000008526012352",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"ocom",
								"003",
								"0000008526012352"
							]
						}
					},
					"response": []
				},
				{
					"name": "Verify Mobile",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"var template = `\r",
									"<style type=\"text/css\">\r",
									"    .tftable {font-size:14px;color:#333333;width:100%;border-width: 1px;border-color: #87ceeb;border-collapse: collapse;}\r",
									"    .tftable th {font-size:18px;background-color:#87ceeb;border-width: 1px;padding: 8px;border-style: solid;border-color: #87ceeb;text-align:left;}\r",
									"    .tftable tr {background-color:#ffffff;}\r",
									"    .tftable td {font-size:14px;border-width: 1px;padding: 8px;border-style: solid;border-color: #87ceeb;}\r",
									"    .tftable tr:hover {background-color:#e0ffff;}\r",
									"</style>\r",
									"\r",
									"<table class=\"tftable\" border=\"1\">\r",
									"    <tr>\r",
									"        <th>Soc No</th>\r",
									"        <th>Cust No</th>\r",
									"        <th>Identifier</th>\r",
									"        <th>Cust Mob No</th>\r",
									"        <th>ISD Code</th>\r",
									"        <th>Old Cust Mob No</th>\r",
									"        <th>Old Mob ISD Code</th>\r",
									"        <th>Verify Flag</th>\r",
									"        <th>Channel ID</th>\r",
									"        <th>Change Date</th>\r",
									"        <th>Maker ID</th>\r",
									"        <th>Checker ID</th>\r",
									"        <th>DOV</th>\r",
									"    </tr>\r",
									"    <tr>\r",
									"        <td>{{response.id.socNo}}</td>\r",
									"        <td>{{response.id.custNo}}</td>\r",
									"        <td>{{response.id.identifier}}</td>\r",
									"        <td>{{response.custMobNo}}</td>\r",
									"        <td>{{response.isdCode}}</td>\r",
									"        <td>{{response.oldCustMobNo}}</td>\r",
									"        <td>{{response.oldMobIsdCode}}</td>\r",
									"        <td>{{response.verifyFlag}}</td>\r",
									"        <td>{{response.chnlId}}</td>\r",
									"        <td>{{response.changeDate}}</td>\r",
									"        <td>{{response.makerId}}</td>\r",
									"        <td>{{response.checkerId}}</td>\r",
									"        <td>{{response.dov}}</td>\r",
									"    </tr>\r",
									"</table>\r",
									"`;\r",
									"\r",
									"function constructVisualizerPayload() {\r",
									"    return {response: pm.response.json()};\r",
									"}\r",
									"\r",
									"pm.visualizer.set(template, constructVisualizerPayload());"
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "PUT",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/cmob/003/0000008526012352/91/7506111495/Y",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cmob",
								"003",
								"0000008526012352",
								"91",
								"7506111495",
								"Y"
							]
						}
					},
					"response": []
				},
				{
					"name": "Find for Verification",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/cmob/003/0000008526022849/91/9819349561",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cmob",
								"003",
								"0000008526022849",
								"91",
								"9819349561"
							]
						}
					},
					"response": []
				},
				{
					"name": "Verify Mobile Number 60039",
					"request": {
						"method": "PUT",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/cmob/003/0000008526022849/91/9819349561/Y",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cmob",
								"003",
								"0000008526022849",
								"91",
								"9819349561",
								"Y"
							]
						}
					},
					"response": []
				},
				{
					"name": "Mobile Amendment",
					"request": {
						"method": "PUT",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/cmob/amend/003/0000008526012345/P/91/7045322020",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cmob",
								"amend",
								"003",
								"0000008526012345",
								"P",
								"91",
								"7045322020"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "PSOF",
			"item": [
				{
					"name": "New PSOF",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"customerType\": \"01\",\r\n    \"psofId\": {\r\n        \"socNo\": \"003\",\r\n        \"custAcctNo\": \"0000008526012356\"\r\n    },\r\n    \"sourceFunds\": \"06\",\r\n    \"createDate\": \"2025-08-25\",\r\n    \"makerId\": \"2199690\",\r\n    \"checkerId\": \"2199691\",\r\n    \"branchNo\": \"00437\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/psof/new",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"psof",
								"new"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "CTPM",
			"item": [
				{
					"name": "New Request",
					"request": {
						"method": "GET",
						"header": []
					},
					"response": []
				}
			]
		},
		{
			"name": "CIF",
			"item": [
				{
					"name": "New Request",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"regionDate\": \"2025-10-06\",\r\n  \"transactionBranch\": \"00437\",\r\n  \"batchTandem\": \"E\",\r\n  \"custTierType\": \"010203\",\r\n  \"resiStatus\": \"1\",\r\n  \"custNameMain\": {\r\n    \"title\": \"01\",\r\n    \"firstName\": \"Pratik\",\r\n    \"middleName\": \"Shankar\",\r\n    \"lastName\": \"Telang\"\r\n  },\r\n  \"custNameMaiden\": {\r\n    \"title\": \"01\",\r\n    \"firstName\": \"Pratik\",\r\n    \"middleName\": \"Shankar\",\r\n    \"lastName\": \"Telang\"\r\n  },\r\n  \"custDob\": \"2001-02-11\",\r\n  \"custGender\": \"M\",\r\n  \"custMaritalStatus\": \"S\",\r\n  \"custFatherName\": {\r\n    \"title\": \"01\",\r\n    \"firstName\": \"Shankar\",\r\n    \"middleName\": \"Vasant\",\r\n    \"lastName\": \"Telang\"\r\n  },\r\n  \"custSpouseName\": {\r\n    \"title\": \"03\",\r\n    \"firstName\": \"Mrunalini\",\r\n    \"middleName\": \"Pratik\",\r\n    \"lastName\": \"Telang\"\r\n  },\r\n  \"custMotherName\": {\r\n    \"title\": \"03\",\r\n    \"firstName\": \"Pratiksha\",\r\n    \"middleName\": \"Shankar\",\r\n    \"lastName\": \"Telang\"\r\n  },\r\n  \"custNoOfDependents\": 2,\r\n  \"custIlliterate\": \"N\",\r\n  \"custGuardianName\": null,\r\n  \"custNationality\": \"IN\",\r\n  \"custOccType\": \"S\",\r\n  \"custOccSubType\": \"0170\",\r\n  \"custAnnIncome\": 850000,\r\n  \"custNetWorth\": 2500000,\r\n  \"custReligion\": \"H\",\r\n  \"custCategory\": \"O\",\r\n  \"custSourceFunds\": \"01\",\r\n  \"custDisability\": \"N\",\r\n  \"custOrganizationName\": \"Tata Consultancy Services Ltd\",\r\n  \"custDesignProfess\": \"Software Engineer\",\r\n  \"custNatureBusiness\": \"IT Services\",\r\n  \"custEduQualification\": \"6\",\r\n  \"custPolExpo\": \"N\",\r\n  \"custISO3166CountryJurisResi\": \"IN\",\r\n  \"custPOB\": \"Thane\",\r\n  \"custISO3166CountryBirth\": \"IN\",\r\n  \"custCitizenship\": \"IN\",\r\n  \"custTaxCountryYN\": \"Y\",\r\n  \"custPanF60None\": \"F\",\r\n  \"custPanNo\": null,\r\n  \"custForm60\": {\r\n    \"subDate\": \"2025-10-12\",\r\n    \"tranDate\": \"2025-10-12\",\r\n    \"agriIncome\": 1010,\r\n    \"otherIncome\": 1010,\r\n    \"panAppliedFlag\": \"Y\",\r\n    \"panAppliedDate\": \"2025-01-01\",\r\n    \"panAckNo\": \"123456789876543\"\r\n  },\r\n  \"custRelativeCode\": \"F\",\r\n  \"custMobNo\": {\r\n    \"isd\": \"+91\",\r\n    \"mobNo\": \"8104606145  \"\r\n  },\r\n  \"custTeleOff\": null,\r\n  \"custTeleRes\": null,\r\n  \"custEmail\": \"pratiktelang112@gmail.com\",\r\n  \"custOvdDetails\": {\r\n    \"ovdDocType\": \"02\",\r\n    \"ovdDocNumber\": \"YFA1372451\",\r\n    \"ovdDocIssueDate\": \"2022-06-20\",\r\n    \"ovdDocExpiryDate\": \"2032-06-20\",\r\n    \"ovdDocIssuedAt\": \"IssuedAt\"\r\n  },\r\n  \"custMainAddressDetails\": {\r\n    \"addrType\": \"01\",\r\n    \"addrProof\": \"04\",\r\n    \"addressLine1\": \"Flat - 603, Shree Ganaraya Tower\",\r\n    \"addressLine2\": \"Ganesh Mandir road\",\r\n    \"addressLine3\": \"Titwala East\",\r\n    \"country\": \"IN\",\r\n    \"state\": \"27\",\r\n    \"district\": \"27517\",\r\n    \"subDistrict\": \"27517147209\",\r\n    \"city\": \"400\",\r\n    \"village\": \"275171472093704\",\r\n    \"pincode\": \"400607\"\r\n  },\r\n  \"sameAsPOA\": true,\r\n  \"custAltAddressDetails\": null,\r\n  \"deemedOVDDocType\": null,\r\n  \"deemedOVDDocNumber\": null,\r\n  \"inbRequested\": \"Y\",\r\n  \"inbDeliveryMode\": \"01\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/CIF/new",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"CIF",
								"new"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "CUID",
			"item": [
				{
					"name": "NEW CUID",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"id\": {\r\n        \"instNo\": \"003\",\r\n        \"custNo\": \"0000008526000001\",\r\n        \"idType\": \"0004\"\r\n    },\r\n    \"idNumber\": \"W2010207\",\r\n    \"idExpiryDate\": null,\r\n    \"idIssueAt\": \"MumbaiPOI\",\r\n    \"idRemark\": \"MainID\",\r\n    \"idMain\": \"YY\",\r\n    \"idIssueDate\": \"2020-01-01\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/cuid/new",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cuid",
								"new"
							]
						}
					},
					"response": []
				},
				{
					"name": "http://localhost:8080/cuid/new",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"id\": {\r\n        \"instNo\": \"003\",\r\n        \"custNo\": \"0000008526000001\",\r\n        \"idType\": \"0004\"\r\n    },\r\n    \"idNumber\": \"W2010207\",\r\n    \"idExpiryDate\": \"2030-01-01\",\r\n    \"idIssueAt\": \"MumbaiPOI\",\r\n    \"idRemark\": \"MainID\",\r\n    \"idMain\": \"YY\",\r\n    \"idIssueDate\": \"2020-01-01\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/cuid/new",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"cuid",
								"new"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Amend PSOF",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"customerType\": \"01\",\r\n    \"psofId\": {\r\n        \"socNo\": \"003\",\r\n        \"custAcctNo\": \"0000008526012356\"\r\n    },\r\n    \"sourceFunds\": \"\",\r\n    \"createDate\": \"2025-08-25\",\r\n    \"makerId\": \"2199690\",\r\n    \"checkerId\": \"2199691\",\r\n    \"branchNo\": \"00437\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/psof/003/0000008526012357",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"psof",
						"003",
						"0000008526012357"
					]
				}
			},
			"response": []
		},
		{
			"name": "New Request",
			"request": {
				"method": "GET",
				"header": []
			},
			"response": []
		}
	]
}