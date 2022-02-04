FROM python:3.9-slim-buster

WORKDIR /coupons

RUN pip3 install flask requests

COPY . .

ENV FLASK_APP=app.py
ENV FLASK_ENV=development
ENV FLASK_DEBUG=0
ENV FLASK_RUN_PORT=8083

EXPOSE 8083

CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]