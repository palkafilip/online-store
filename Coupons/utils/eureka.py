import requests
import os

EUREKA_ADDRESS = os.environ.get("EUREKA_ADDRESS")
APP_IP = os.environ.get("APP_IP")


def get_eureka_apps():
    api_url = f"http://{EUREKA_ADDRESS}/eureka/apps/"
    headers = {"Content-Type": "application/json", "Accept": "application/json"}
    response = requests.get(api_url, headers=headers)
    return response


def register_app_in_eureka():
    body = {
        "instance": {
            "instanceId": "DESKTOP-6EFKTD0:coupons-app:8083",
            "app": "COUPONS-APP",
            "appGroupName": "null",
            "ipAddr": APP_IP,
            "sid": "na",
            "homePageUrl": f"http://{APP_IP}:8083/",
            "statusPageUrl": f"http://{APP_IP}:8083/actuator/info",
            "healthCheckUrl": f"http://{APP_IP}:8083/actuator/health",
            "secureHealthCheckUrl": "null",
            "vipAddress": "coupons-app",
            "secureVipAddress": "coupons-app",
            "countryId": 1,
            "dataCenterInfo": {
                "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
                "name": "MyOwn"
            },
            "hostName": f"{APP_IP}",
            "status": "UP",
            "overriddenStatus": "UNKNOWN",
            "leaseInfo": {
                "renewalIntervalInSecs": 30,
                "durationInSecs": 90,
                "registrationTimestamp": 0,
                "lastRenewalTimestamp": 0,
                "evictionTimestamp": 0,
                "serviceUpTimestamp": 0
            },
            "isCoordinatingDiscoveryServer": "false",
            "lastUpdatedTimestamp": 1640945743957,
            "lastDirtyTimestamp": 1640945744579,
            "actionType": "null",
            "asgName": "null",
            "port": {
                "$": 8083,
                "@enabled": "true"
            },
            "securePort": {
                "$": 443,
                "@enabled": "false"
            },
            "metadata": {
                "management.port": "8083"
            }
        }
    }
    api_url = f"http://{EUREKA_ADDRESS}/eureka/apps/COUPONS-APP"
    response = requests.post(api_url, json=body)
    return response
