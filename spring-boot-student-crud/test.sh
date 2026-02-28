#!/bin/bash
# test.sh - CRUD операции за Student API на порт 8082
# Стартирай приложението преди да пуснеш този скрипт:
#   java -jar target/crud-0.0.1-SNAPSHOT.jar

BASE_URL="http://localhost:8082/api/students"

echo "=== 1. CREATE - Добавяне на студенти ==="
echo ""
echo "POST /api/students (Ivan Petrov):"
curl -s -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Ivan","lastName":"Petrov","age":18,"grade":"12A"}' | python3 -m json.tool
echo ""

echo "POST /api/students (Maria Ivanova):"
curl -s -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Maria","lastName":"Ivanova","age":17,"grade":"11B"}' | python3 -m json.tool
echo ""

echo "=== 2. READ - Четене на всички студенти ==="
echo ""
echo "GET /api/students:"
curl -s -X GET "$BASE_URL" | python3 -m json.tool
echo ""

echo "=== 3. READ - Четене на студент по ID ==="
echo ""
echo "GET /api/students/1:"
curl -s -X GET "$BASE_URL/1" | python3 -m json.tool
echo ""

echo "=== 4. UPDATE - Обновяване на студент ==="
echo ""
echo "PUT /api/students/1:"
curl -s -X PUT "$BASE_URL/1" \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Ivan","lastName":"Petrov","age":19,"grade":"12B"}' | python3 -m json.tool
echo ""

echo "=== 5. READ - Проверка след обновяване ==="
echo ""
echo "GET /api/students/1:"
curl -s -X GET "$BASE_URL/1" | python3 -m json.tool
echo ""

echo "=== 6. DELETE - Изтриване на студент ==="
echo ""
echo "DELETE /api/students/2:"
curl -s -o /dev/null -w "HTTP Status: %{http_code}\n" -X DELETE "$BASE_URL/2"
echo ""

echo "=== 7. READ - Проверка след изтриване ==="
echo ""
echo "GET /api/students:"
curl -s -X GET "$BASE_URL" | python3 -m json.tool
echo ""

echo "=== 8. GET несъществуващ студент ==="
echo ""
echo "GET /api/students/99:"
curl -s -o /dev/null -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/99"
echo ""

echo "=== Готово! Всички CRUD операции са изпълнени. ==="
