import time

from flask import Flask
from utils.eureka import get_eureka_apps, register_app_in_eureka

app = Flask(__name__)

import controllers.coupons


def register_in_eureka():
    print('Tyring to register in eureka')
    try:
        response = get_eureka_apps()
        if response.status_code == 200:
            register_app_in_eureka()
            print('Sucessfully registered application in Eureka serivce!')
    except Exception:
        print('Eureka service is not active, waiting 5 seconds and trying again')
        time.sleep(5)
        register_in_eureka()


with app.app_context():
    register_in_eureka()

if __name__ == '__main__':
    app.run()
