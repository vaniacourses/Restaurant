from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
import time

# como executar:
# 1 - possuir a pasta 'venv' dentro da pasta 'selenium'
# 2 - rodar venv\Scripts\activate na pasta 'selenium'
# 3 - entrar na pasta 'code' e rodar o script: python teste.py

def testar_checkout(driver, primeiro_nome, ultimo_nome, email, pais, endereco1, endereco2, cidade, cep, estado, telefone, cartao_credito, expiracao_mes, expiracao_ano, cvv, nome_cartao, marcarCheckBox):
    try:

        url_home = "http://localhost:9000/"
        driver.get(url_home)

        input('Adicione um produto no carrinho e depois toque para continuar...')

        url_checkout = "http://localhost:9000/checkout"
        driver.get(url_checkout)

        time.sleep(2)

        # Primeiro Nome
        primeiro_nome_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[1]/div/input"
        primeiro_nome_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, primeiro_nome_xpath))
        )
        primeiro_nome_input.clear()
        primeiro_nome_input.send_keys(primeiro_nome)

        # Último Nome
        ultimo_nome_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[2]/div/input"
        ultimo_nome_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, ultimo_nome_xpath))
        )
        ultimo_nome_input.clear()
        ultimo_nome_input.send_keys(ultimo_nome)

        # Email
        email_xpath = "//input[@id='email']"
        email_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, email_xpath))
        )
        email_input.clear()
        email_input.send_keys(email)

        # País
        pais_xpath = "//select[@id='country-drop-down']"
        pais_select = Select(WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, pais_xpath))
        ))
        pais_select.select_by_visible_text(pais)

        # Endereço 1
        endereco1_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[5]/div/input"
        endereco1_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, endereco1_xpath))
        )
        endereco1_input.clear()
        endereco1_input.send_keys(endereco1)

        # Endereço 2
        endereco2_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[6]/div/input"
        endereco2_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, endereco2_xpath))
        )
        endereco2_input.clear()
        endereco2_input.send_keys(endereco2)

        script = "window.scrollTo(0, document.body.scrollHeight);"
        driver.execute_script(script)

        # Cidade
        cidade_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[7]/div/input"
        cidade_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, cidade_xpath))
        )
        cidade_input.clear()
        cidade_input.send_keys(cidade)

        # CEP
        cep_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[8]/div/input"
        cep_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, cep_xpath))
        )
        cep_input.clear()
        cep_input.send_keys(cep)

        driver.execute_script(script)

        # Estado
        estado_xpath = "//select[@id='state-drop-down']"
        estado_select = Select(WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, estado_xpath))
        ))
        estado_select.select_by_visible_text(estado)

        # Telefone
        telefone_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[10]/div/input"
        telefone_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, telefone_xpath))
        )
        telefone_input.clear()
        telefone_input.send_keys(telefone)

        if (marcarCheckBox):
            # Marcar checkbox 'Make it my default'
            default_checkbox_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[1]/div[11]/div/input[1]"
            default_checkbox = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.XPATH, default_checkbox_xpath))
            )
            if not default_checkbox.is_selected():
                default_checkbox.click()

        script = "window.scrollTo(0, document.body.scrollHeight);"
        driver.execute_script(script)

        # Número do Cartão de Crédito
        cartao_credito_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[2]/div/div/div[1]/div/input"
        cartao_credito_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, cartao_credito_xpath))
        )
        cartao_credito_input.clear()
        cartao_credito_input.send_keys(cartao_credito)

        # Mês de Expiração
        expiracao_mes_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[2]/div/div/div[2]/div/input"
        expiracao_mes_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, expiracao_mes_xpath))
        )
        expiracao_mes_input.clear()
        expiracao_mes_input.send_keys(expiracao_mes)

        # Ano de Expiração
        expiracao_ano_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[2]/div/div/div[3]/div/input"
        expiracao_ano_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, expiracao_ano_xpath))
        )
        expiracao_ano_input.clear()
        expiracao_ano_input.send_keys(expiracao_ano)

        # CVV
        cvv_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[2]/div/div/div[4]/div/input"
        cvv_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, cvv_xpath))
        )
        cvv_input.clear()
        cvv_input.send_keys(cvv)

        # Nome no Cartão
        nome_cartao_xpath = "/html/body/div[2]/div/section/div/form/div/div[1]/div[2]/div/div/div[5]/div/input"
        nome_cartao_input = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, nome_cartao_xpath))
        )
        nome_cartao_input.clear()
        nome_cartao_input.send_keys(nome_cartao)

        driver.execute_script("window.scrollTo(0, 0);")
        time.sleep(1)

        # Botão de Place Order
        place_order_xpath = "/html/body/div[2]/div/section/div/form/div/div[2]/button"
        place_order_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, place_order_xpath))
        )
        place_order_button.click()

        print("Pedido realizado.")

        # verificar se houve sucesso no pedido
        try:
            success_message_xpath = "/html/body/div[2]/div/div/div[1]/div/h2"
            success_message = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.XPATH, success_message_xpath))
            )
            assert success_message.text == "Congratulations!, Your order has been placed.", "Mensagem de sucesso não encontrada após a submissão do pedido."
            
            print("Pedido realizado com sucesso. Mensagem de sucesso verificada.")

        except Exception as e:
            print(f"Erro ao verificar a mensagem de sucesso: {e}")

    except Exception as e:
        print(f"Erro durante a execução do teste: {e}")

if __name__ == "__main__":
    driver = webdriver.Chrome()
    
    testar_checkout(driver, "Teste", "Sobrenome", "teste@gmail.com", "USA", "Rua A", "Apto 123", "Rio de Janeiro", "12345", "Texas", "1234567890", "1234567890123456", "12", "2025", "123", "Teste nome", True)
    input('Toque para encerrar...')

    driver.quit()
