from django.test import LiveServerTestCase, TestCase, tag
from django.urls import reverse
from selenium import webdriver


@tag('functional')
class FunctionalTestCase(LiveServerTestCase):
    