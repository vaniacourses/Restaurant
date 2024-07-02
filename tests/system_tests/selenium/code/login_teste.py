from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# como executar:
# 1 - possuir a pasta 'venv' dentro da pasta 'selenium'
# 2 - rodar venv\Scripts\activate na pasta 'selenium'
# 3 - entrar na pasta 'code' e rodar o script: python teste.py

# TESTE PARA VALIDAR LOGIN NO SISTEMA

def realizar_login(driver, email, senha):
    url = "http://localhost:9000/login"
    driver.get(url)

    time.sleep(1)

    assert "Login" in driver.title, "Página de login não carregou corretamente."
    print("Página de login carregada com sucesso.")

    try:
        
        # preencher o campo de e-mail
        email_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[1]/input"
        email_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, email_xpath))
        )
        email_input.clear()
        email_input.send_keys(email)

        # preencher o campo de senha
        senha_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[2]/input"
        senha_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, senha_xpath))
        )
        senha_input.clear()
        senha_input.send_keys(senha)

        # clicar no botão de login
        login_button_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[4]/button"
        login_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, login_button_xpath))
        )
        login_button.click()
        
        # verificar se o login foi bem-sucedido verificando o título da página inicial
        WebDriverWait(driver, 10).until(
            EC.title_contains("My Restaurant")
        )
        assert "My Restaurant" in driver.title, "Falha ao redirecionar para a página inicial após o login."
        print("Login realizado com sucesso.")

    except Exception as e:
        # verificar se o login falhou
        error_message_xpath = "/html/body/div[2]/div/section/div/div/div[2]/div[2]/div/strong"
        try:
            error_message = WebDriverWait(driver, 5).until(
                EC.presence_of_element_located((By.XPATH, error_message_xpath))
            )
            assert "Invalid Email or Password" in error_message.text, "Erro de login não encontrado."
            print(f"Falha no login: {error_message.text}")
        except:
            print(f"Erro durante a execução do teste: {e}")

    input('Toque para encerrar...')

if __name__ == "__main__":
    driver = webdriver.Chrome()
    try:
        email = "teste@gmail.com"
        senha = "teste"
        realizar_login(driver, email, senha)
    finally:
        driver.quit()
