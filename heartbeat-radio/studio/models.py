from django.db import models
from django.utils import timezone

class Sensor(models.Model):
    serial_number = models.CharField(max_length=250, unique=True)
    name = models.CharField(max_length=250)
    location = models.CharField(max_length=250)
    date_created = models.DateTimeField(default=timezone.now)

    class Meta:
        ordering = ['id']

class Heartbeat(models.Model):
    serial_number = models.CharField(max_length=250)
    date_created = models.DateTimeField(default=timezone.now)

    class Meta:
        ordering = ['id']

class Loan(models.Model):
    # We could use validators or schema mapping so we don't have
    # to adapt to this specific API's syntax, but I ran out of time here
    idNumber = models.CharField(max_length=250)
    amount = models.FloatField()
    interestRate = models.FloatField()
    length = models.IntegerField()
    paymentAmount = models.FloatField()

    class Meta:
        ordering = ['idNumber']
