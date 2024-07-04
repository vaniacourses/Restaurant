from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# como executar:
# 1 - possuir a pasta 'venv' dentro da pasta 'selenium'
# 2 - rodar venv\Scripts\activate na pasta 'selenium'
# 3 - entrar na pasta 'code' e rodar o script: python teste.py

def testar_cadastro(driver, email, primeiro_nome, ultimo_nome, senha):
    url = "http://localhost:9000/registration"
    driver.get(url)

    time.sleep(1)

    assert "Registration" in driver.title, "Página de registro não carregou corretamente"
    print("Página de registro carregada com sucesso")

    try:
        email_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[1]/input"
        email_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, email_xpath))
        )
        email_input.clear()
        email_input.send_keys(email)

        primeiro_nome_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[2]/input"
        primeiro_nome_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, primeiro_nome_xpath))
        )
        primeiro_nome_input.clear()
        primeiro_nome_input.send_keys(primeiro_nome)

        ultimo_nome_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[3]/input"
        ultimo_nome_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, ultimo_nome_xpath))
        )
        ultimo_nome_input.clear()
        ultimo_nome_input.send_keys(ultimo_nome)

        senha_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[4]/input"
        senha_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, senha_xpath))
        )
        senha_input.clear()
        senha_input.send_keys(senha)

        # clicar no botão de registro
        register_button_xpath = "//button[@type='submit' and text()='Register']"
        register_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, register_button_xpath))
        )
        driver.execute_script("arguments[0].click();", register_button)

        WebDriverWait(driver, 10).until(
            EC.url_to_be("http://localhost:9000/login")
        )
        print("Redirecionado para a página de login.")

        # verificar se o texto de sucesso está presente na página de login
        sucesso_xpath = "//div[@class='alert alert-info alert-dismissible fade show' and contains(., 'You have successfully registered.')]"
        success_message = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, sucesso_xpath))
        )
        assert success_message is not None, "Mensagem de sucesso de registro não encontrada."
        print("Cadastro realizado com sucesso e mensagem de sucesso confirmada.")

    except Exception as e:
        try:
            # mensagem de erro do campo de email
            email_error_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[1]/span"
            email_error_message = driver.find_element(By.XPATH, email_error_xpath).text
            print(f"Erro no campo de email: {email_error_message}")

            # mensagem de erro do campo de primeiro nome
            primeiro_nome_error_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[2]/span"
            primeiro_nome_error_message = driver.find_element(By.XPATH, primeiro_nome_error_xpath).text
            print(f"Erro no campo de primeiro nome: {primeiro_nome_error_message}")

            # mensagem de erro do campo de último nome
            ultimo_nome_error_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[3]/span"
            ultimo_nome_error_message = driver.find_element(By.XPATH, ultimo_nome_error_xpath).text
            print(f"Erro no campo de último nome: {ultimo_nome_error_message}")

            # mensagem de erro do campo de senha
            senha_error_xpath = "/html/body/div[2]/div/section/div/div/div[2]/form/div/div[4]/span"
            senha_error_message = driver.find_element(By.XPATH, senha_error_xpath).text
            print(f"Erro no campo de senha: {senha_error_message}")

        except:
            print(f"Erro durante a execução do teste: {e}")

    input('Toque para encerrar...')

if __name__ == "__main__":
    driver = webdriver.Chrome()
    try:
        testar_cadastro(driver, "", "", "", "")
    finally:
        driver.quit()
